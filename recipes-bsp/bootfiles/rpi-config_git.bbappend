# Audio configuration: HDMI no audio, Bluetooth/WiFi disabled, USB DAC enabled
do_deploy:append() {
    # Remove vc4-kms-v3d if it was added without noaudio
    sed -i '/^dtoverlay=vc4-kms-v3d[^,]/d' ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt

    echo "" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
    echo "dtoverlay=vc4-kms-v3d,noaudio" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
    echo "dtoverlay=disable-bt-pi5" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
    echo "dtoverlay=disable-wifi-pi5" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
}
