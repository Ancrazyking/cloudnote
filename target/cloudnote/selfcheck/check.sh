#!/bin/bash
result=`curl -s http://192.168.25.135:8080/inote/selfcheck/check.html`
if [[ $result = *ok* ]]; then
echo 'success'
else
echo 'error'
fi
