# Use RAM as root filesystem when booting from initramfs
CMDLINE_ROOTFS = "root=/dev/ram0"

# CPU isolation for audio performance
CMDLINE:append = " isolcpus=3 nohz_full=3 rcu_nocbs=3"
