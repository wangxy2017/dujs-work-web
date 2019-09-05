#!/bin/sh

ps ux | grep "dujs-work-web.jar" | grep -v grep | grep -v stop.sh | cut -c 9-15 | xargs kill

echo "stopped!"
