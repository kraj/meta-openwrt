# meta-openwrt
OE metadata layer for OpenWRT

This layer provides OpenEmbedded metadata for [OpenWRT](http://www.openwrt.org/) packages

# Getting Started

```shell
git clone git://github.com/openembedded/openembedded-core.git
cd openembeeded-core
git clone git://github.com/openembedded/meta-openembedded.git
git clone git://github.com/openembedded/bitbake.git
git clone git://github.com/kraj/meta-openwrt.git

$ . ./oe-init-build-env
```

Edit conf/bblayers.conf to add meta-musl to layer mix e.g.

```python
BBLAYERS ?= " \
  /home/kraj/openembedded-core/meta-openwrt \
  /home/kraj/openembedded-core/meta-openembedded/meta-oe \
  /home/kraj/openembedded-core/meta \
  "
```

# Building

Below we build for qemuarm machine as an example, add
one of OpenWRT recipes to images e.g. in conf/local.conf add

CORE_IMAGE_EXTRA_INSTALL = "libubox-examples"

```shell
$ TCLIBC=musl MACHINE=qemuarm bitbake core-image-minimal
$ TCLIBC=musl MACHINE=qemux86 bitbake core-image-weston
$ TCLIBC=musl MACHINE=qemux86 bitbake core-image-sato

```
# Running

```shell
$ TCLIBC=musl runqemu qemuarm
```

# Limitations

Works with OE Release >= 2.0

Currently images are buildable/bootable for mips, arm, aarch64, ppc, x86, x86_64
based qemu machines
MACHINE variable and TCLIBC variables can also be set in conf/local.conf
to avoid typing it on commandline on any bitbake invocation

# Dependencies

```
URI: git://github.com/openembedded/openembedded-core.git
branch: master
revision: HEAD

URI: git://github.com/openembedded/meta-openembedded.git
branch: master
revision: HEAD

URI: git://github.com/openembedded/bitbake.git
branch: master
revision: HEAD
```

# Upstreaming

## Mailing List
Send pull requests to openembedded-devel@lists.openembedded.org with '[meta-openwrt]' in the subject'

When sending single patches, please use something like

```shell
git send-email -M -1 --to openembedded-devel@lists.openembedded.org --subject-prefix=meta-openwrt][PATCH
```
## Forking via github

You are encouraged to fork the mirror on [github](https://github.com/kraj/meta-openwrt/)
to share your patches, this is preferred for patch sets consisting of more than
one patch. Other services like gitorious, repo.or.cz or self hosted setups are
of course accepted as well, 'git fetch <remote>' works the same on all of them.
We recommend github because it is free, easy to use, has been proven to be reliable
and has a really good web GUI.

Layer Maintainer: Khem Raj raj.khem@gmail.com
