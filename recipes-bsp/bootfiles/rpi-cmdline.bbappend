# Use RAM as root filesystem when booting from initramfs
CMDLINE_ROOTFS = "root=/dev/ram0"

# IRQ affinity and performance tuning
CMDLINE:append = " irqaffinity=0-2 elevator=none threadirqs"
