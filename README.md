# RedCraftReboot

This plugin has been built to warn players about reboots, and make a countdown.

## How it works

- One command, `/reboot <minutes> <reason>`
- If minutes set to 0, start the countdown from 10 sec
- Max minutes is defaulted to 15
- Broadcast who asked for a reboot and why
- Args not needed for console, default to 15 min and "scheduled reboot"
- Warn in the chat 15 min, 10 min, 5 min, 4 min, 3 min, 2 min before reboot
- Start a countdown in the boss bar until reboot
- Do a countdown from 10 to 0 as title and end with "Reboot!"
- Use fireworks for the 10 last seconds

## Permissions

- `redcraftreboot.reboot`: permission to schedule a reboot

## Contributing

You are free to suggest changes by opening an issue ticket.

You can also open PRs, remember to bump the version in `pom.xml` and `plugin.yml` before opening a pull request.
