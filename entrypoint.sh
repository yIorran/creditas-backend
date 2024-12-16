#!/bin/bash

docker build -t cb .

docker run -d -p 8087:8087 --name creditas-backend cb