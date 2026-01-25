# Audio configuration: HDMI no audio, Bluetooth/WiFi disabled, USB DAC primary, HiFiBerry fallback
do_deploy:append() {
    # Remove vc4-kms-v3d if it was added without noaudio
    sed -i '/^dtoverlay=vc4-kms-v3d[^,]/d' ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt

    echo "" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
    echo "dtoverlay=vc4-kms-v3d,noaudio" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
    echo "dtparam=audio=off" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
    echo "dtparam=i2s=on" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
    echo "dtparam=i2c_arm=on" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
    echo "dtoverlay=disable-bt-pi5" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
    echo "dtoverlay=disable-wifi-pi5" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt

    # Enable HiFiBerry Digi+ Pro fallback support
    # Note: HiFiBerry Digi+ Pro overlay includes I2S configuration
    echo "dtoverlay=hifiberry-digi-pro" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt

    # Disable unused peripherals to reduce DMA competition (except I2S and I2C for HiFiBerry)
    echo "dtparam=spi=off" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt

    # CPU performance tuning
    echo "force_turbo=1" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
    echo "arm_freq=1500" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
}
