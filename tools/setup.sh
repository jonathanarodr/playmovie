#!/bin/bash

# base: https://github.com/android/nowinandroid/blob/main/tools/setup.sh

git_dir=$(git rev-parse --git-dir 2> /dev/null)
git_root=$(git rev-parse --show-toplevel 2> /dev/null)

function install_hook() {
    hook_name=$1
    cp -R "${git_root}/tools/hooks/${hook_name}" "${git_dir}/hooks/${hook_name}" \
        && chmod +x "${git_dir}/hooks/${hook_name}"
}

mkdir -p "${git_dir}/hooks/"

echo "[INFO] Installing git pre-commit hook..."
install_hook pre-commit

echo "[INFO] Successfully configured hooks"

echo
echo "SETUP SUCCESSFUL"