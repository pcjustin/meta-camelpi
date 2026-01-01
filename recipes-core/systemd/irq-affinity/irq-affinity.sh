#!/bin/sh
# Configure IRQ affinity to exclude isolated CPU core 3
# Bind all IRQs to cores 0-2 (mask: 0x07)

AFFINITY_MASK="7"

set_irq_affinity() {
    local irq=$1
    local irq_path="/proc/irq/$irq"

    if [ ! -d "$irq_path" ] || [ ! -w "$irq_path/smp_affinity" ]; then
        return
    fi

    # Skip per-CPU IRQs (they're already pinned)
    if [ -f "$irq_path/per_cpu_count" ]; then
        return
    fi

    echo "$AFFINITY_MASK" > "$irq_path/smp_affinity" 2>/dev/null || true
}

# Process all IRQs
for irq in /proc/irq/[0-9]*; do
    irq_num=$(basename "$irq")
    set_irq_affinity "$irq_num"
done

# Log critical IRQ assignments for verification
echo "Critical IRQ assignments:" > /var/log/irq-affinity.log
for irq in /proc/irq/[0-9]*; do
    irq_num=$(basename "$irq")
    irq_desc=$(cat "$irq/actions" 2>/dev/null | head -n1)
    irq_aff=$(cat "$irq/smp_affinity" 2>/dev/null)

    case "$irq_desc" in
        *usb*|*USB*|*dwc*|*xhci*|*timer*|*dma*)
            echo "IRQ $irq_num ($irq_desc): affinity=$irq_aff" >> /var/log/irq-affinity.log
            ;;
    esac
done

exit 0
