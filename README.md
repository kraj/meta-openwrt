# meta-openwrt
OE metadata layer for OpenWRT

This layer provides OpenEmbedded metadata for [OpenWRT](http://www.openwrt.org/) packages

# Getting Started

```shell
git clone https://github.com/openembedded/openembedded-core.git
cd openembedded-core
git clone https://github.com/openembedded/meta-openembedded.git
git clone https://github.com/openembedded/bitbake.git
git clone https://github.com/kraj/meta-openwrt.git

$ . ./oe-init-build-env

$ bitbake-layers add-layer ../meta-openembedded/meta-oe
$ bitbake-layers add-layer ../meta-openembedded/meta-python
$ bitbake-layers add-layer ../meta-openembedded/meta-networking
$ bitbake-layers add-layer ../meta-openwrt
```

# Building

Below we build for qemuarm machine as an example.
to local.conf add:

```
INHERIT += " openwrt-distro-defaults "
```

You can then use, for example, one of:

```shell
$ TCLIBC=musl MACHINE=qemuarm bitbake openwrt-image-minimal
$ TCLIBC=musl MACHINE=qemuarm bitbake openwrt-image-base
$ TCLIBC=musl MACHINE=qemuarm bitbake openwrt-image-full

```

* openwrt-image-minimal has openwrt networking and cli but no UI
* openwrt-image-base has openwrt networking, cli, and UI (luci)

# Running

```shell
$ TCLIBC=musl runqemu qemuarm
```

# Limitations

Works with OE Release >= 2.5 ( Sumo )

Currently images are buildable/bootable for mips, arm, aarch64, ppc, x86, x86_64
based qemu machines
MACHINE variable and TCLIBC variables can also be set in conf/local.conf
to avoid typing it on commandline on any bitbake invocation

# Dependencies

```
URI: https://github.com/openembedded/openembedded-core.git
branch: master
revision: HEAD

URI: https://github.com/openembedded/meta-openembedded.git
branch: master
revision: HEAD

URI: https://github.com/openembedded/bitbake.git
branch: master
revision: HEAD

URI: https://github.com/imyller/meta-nodejs.git
branch: master
revision: HEAD

URI: https://github.com/imyller/meta-nodejs-contrib.git
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

Maintainer(s)

* Khem Raj <mailto:raj.khem@gmail.com>
