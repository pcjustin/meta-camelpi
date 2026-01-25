#!/bin/sh
# Detect and log available audio hardware

AUDIO_LOG="/var/log/audio-detect.log"

log_message() {
    echo "[$(date '+%Y-%m-%d %H:%M:%S')] $1" | tee -a "$AUDIO_LOG"
}

log_message "=== Audio Hardware Detection ==="

# Wait for devices to initialize
sleep 2

# List all sound cards
log_message "Available sound cards:"
cat /proc/asound/cards | tee -a "$AUDIO_LOG"

# Check for USB audio
if grep -q "USB Audio" /proc/asound/cards; then
    log_message "USB DAC detected - using as primary audio device"
elif grep -q "sndrpihifiberry" /proc/asound/cards; then
    log_message "HiFiBerry Digi+ Pro detected - using as fallback audio device"
else
    log_message "WARNING: No supported audio device detected!"
fi

# List PCM devices
log_message "Available PCM devices:"
aplay -l | tee -a "$AUDIO_LOG"

log_message "=== Audio detection complete ==="
exit 0
