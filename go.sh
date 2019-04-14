#!/usr/bin/env bash

set -euo pipefail

./gradlew --quiet installDist
./build/install/shokunin-game-of-pawns/bin/shokunin-game-of-pawns $@