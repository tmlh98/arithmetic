#!/bin/bash

msg=$1

if [ ! -n "$msg" ]; then  
	msg="."
fi

# add
git add -A
echo '---------- (git add) end ----------'

# commit
git commit -m "$msg"
echo "---------- (git commit) end ----------"

# push
git push origin master
echo '---------- (push origin master) end ----------'

echo 'over...'


