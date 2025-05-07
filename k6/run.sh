#!/bin/dash

k6 run gateway-rate-limit-test.js --out influxdb=http://admin:admin123@localhost:8086/k6