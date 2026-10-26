#!/bin/sh
set -e

java -jar /app/car.jar &
CAR_PID=$!

java -jar /app/main.jar &
MAIN_PID=$!

# Stop both applications when the container stops.
trap 'kill "$CAR_PID" "$MAIN_PID" 2>/dev/null || true' TERM INT

wait "$MAIN_PID"
