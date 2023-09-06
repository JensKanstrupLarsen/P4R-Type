#!/bin/bash

# Print script commands and exit on errors.
set -xe

# --- Mininet --- #
MININET_COMMIT="aa0176fce6fb718a03474f8719261b07b670d30d"  # 2022-Apr-02
git clone https://github.com/mininet/mininet mininet
cd mininet
git checkout ${MININET_COMMIT}
PATCH_DIR="${HOME}/patches"
patch -p1 < "${PATCH_DIR}/mininet-dont-install-python2-2022-apr.patch" || echo "Errors while attempting to patch mininet, but continuing anyway ..."
cd ..
# TBD: Try without installing openvswitch, i.e. no '-v' option, to see
# if everything still works well without it.
sudo ./mininet/util/install.sh -nw

find /usr/lib /usr/local $HOME/.local | sort > $HOME/usr-local-7-after-mininet-install.txt

# --- Utils --- #
git clone https://github.com/p4lang/tutorials
sudo mv tutorials/utils /home/safeP4R
sudo rm -rf tutorials
sudo chown -R safeP4R:safeP4R /home/safeP4R/utils

sudo chown -R safeP4R:safeP4R files
sudo chmod +x files/send.py
sudo chmod +x files/receive.py
sudo mv files/* /home/safeP4R
sudo rm -rf files

# --- Emacs --- #
sudo cp p4_16-mode.el /usr/share/emacs/site-lisp/
sudo mkdir /home/safeP4R/.emacs.d/
echo "(autoload 'p4_16-mode' \"p4_16-mode.el\" \"P4 Syntax.\" t)" > init.el
echo "(add-to-list 'auto-mode-alist '(\"\\.p4\\'\" . p4_16-mode))" | tee -a init.el
sudo mv init.el /home/safeP4R/.emacs.d/
sudo ln -s /usr/share/emacs/site-lisp/p4_16-mode.el /home/safeP4R/.emacs.d/p4_16-mode.el
sudo chown -R safeP4R:safeP4R /home/safeP4R/.emacs.d/

# --- Vim --- #
cd ~
mkdir .vim
cd .vim
mkdir ftdetect
mkdir syntax
echo "au BufRead,BufNewFile *.p4      set filetype=p4" >> ftdetect/p4.vim
echo "set bg=dark" >> ~/.vimrc
sudo mv ~/.vimrc /home/safeP4R/.vimrc
cp ~/p4.vim syntax/p4.vim
cd ~
sudo mv .vim /home/safeP4R/.vim
sudo chown -R safeP4R:safeP4R /home/safeP4R/.vim
sudo chown safeP4R:safeP4R /home/safeP4R/.vimrc

# --- Adding Desktop icons --- #
DESKTOP=/home/${USER}/Desktop
mkdir -p ${DESKTOP}

cat > ${DESKTOP}/Terminal.desktop << EOF
[Desktop Entry]
Encoding=UTF-8
Type=Application
Name=Terminal
Name[en_US]=Terminal
Icon=konsole
Exec=/usr/bin/x-terminal-emulator
Comment[en_US]=
EOF

cat > ${DESKTOP}/Wireshark.desktop << EOF
[Desktop Entry]
Encoding=UTF-8
Type=Application
Name=Wireshark
Name[en_US]=Wireshark
Icon=wireshark
Exec=/usr/bin/wireshark
Comment[en_US]=
EOF

sudo mkdir -p /home/safeP4R/Desktop
sudo mv /home/${USER}/Desktop/* /home/safeP4R/Desktop
sudo chown -R safeP4R:safeP4R /home/safeP4R/Desktop/

# Do this last!
sudo reboot
