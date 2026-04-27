import os

string_files = [
    'src/main/res/values/strings.xml',
    'src/main/res/values-tr/strings.xml'
]

# We need to make sure the default values/strings.xml has these missing strings since AAPT complained about them missing default values.
with open('src/main/res/values/strings.xml', 'r') as f:
    content = f.read()

missing_strings = [
    '    <string name="about_kde">About KDE</string>',
    '    <string name="about_kde_about">About KDE Info</string>',
    '    <string name="about_kde_join_kde">Join KDE</string>',
    '    <string name="about_kde_report_bugs_or_wishes">Report bugs or wishes</string>',
    '    <string name="about_kde_support_kde">Support KDE</string>',
    '    <string name="device_icon_description">Device Icon</string>',
    '    <string name="encryption_info_msg_no_ssl">No SSL</string>',
    '    <string name="error_could_not_send_package">Error sending package</string>',
    '    <string name="notification_channel_sms_mms">SMS/MMS</string>',
    '    <string name="plugin_photo_desc">Photo Plugin</string>',
    '    <string name="pref_plugin_mousepad_desc">Mousepad Plugin</string>',
    '    <string name="sftp_camera">Camera</string>',
    '    <string name="sftp_readonly">Read-only</string>',
    '    <string name="sftp_sdcard">SD Card</string>',
    '    <string name="sftp_sdcard_num">SD Card %1$d</string>',
    '    <string name="take_picture">Take Picture</string>',
    '    <string name="kde_connect">NF Connect</string>',
]

if 'about_kde' not in content:
    content = content.replace('</resources>', '\n'.join(missing_strings) + '\n</resources>')

with open('src/main/res/values/strings.xml', 'w') as f:
    f.write(content)
