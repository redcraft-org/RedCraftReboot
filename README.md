# RedCraftReboot

This plugin has been built to warn players about reboots.

## How it works

- One command, `/reboot <minutes> <reason>`
- If minutes set to 0, start the countdown from 10 sec
- Max minutes should be 15
- Broadcast who asked for a reboot and why when ran except if console without args
- Args not needed for console, default to 15 min and "Scheduled daily reboot"
- Warn in the tchat 15 min, 10 min, 5 min, 4 min, 3 min, 2 min before reboot
- Start a countdown in the bossbar until reboot
- Do a countdown from 10 to 0 as title and end with "Reboot!"
- Use fireworks for the 10 last seconds

## How to configure

TODO

## Permissions

- `redcraftreboot.reboot`: permission to schedule a reboot

## Contributing

You are free to suggest changes by opening an issue ticket.

You can also open PRs, remember to bump the version in `pom.xml` and `plugin.yml` before opening a pull request.
