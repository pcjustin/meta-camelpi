# Use RAM as root filesystem when booting from initramfs
CMDLINE_ROOTFS = "root=/dev/ram0"

# IRQ affinity and performance tuning
CMDLINE:append = " irqaffinity=0-2 elevator=none threadirqs"

# Reduce jitter on CPU 3 for audio processing
CMDLINE:append = " nohz_full=3 rcu_nocbs=3"
