PACKAGECONFIG = "alsa daemon fifo flac httpd mpg123 opus sndfile upnp vorbis wavpack zlib"
EXTRA_OECONF = "--enable-upnp"

# Disable default mpd.service, use mpd-camel.service instead
SYSTEMD_AUTO_ENABLE = "disable"
