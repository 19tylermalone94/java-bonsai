# Maintainer: Tyler Malone <github.com/19tylermalone94>

pkgname=java-bonsai
pkgver=1.0.0
pkgrel=1
pkgdesc="A program for generating pixel bonsai trees written in Java."
arch=('any')
url="https://github.com/19tylermalone94/java-bonsai"
license=('MIT')
depends=('java-runtime')

source=("https://github.com/19tylermalone94/java-bonsai/releases/download/v${pkgver}/${pkgname}-${pkgver}.tar.gz")

package() {
  cd "$srcdir/${pkgname}"
  mkdir -p "${pkgdir}/usr/share/java-bonsai"
  cp -r * "${pkgdir}/usr/share/java-bonsai"
  chmod +x "${pkgdir}/usr/share/java-bonsai/java-bonsai.sh"
  ln -s "/usr/share/java-bonsai/java-bonsai.sh" "${pkgdir}/usr/bin/java-bonsai"
}

sha256sums=('e14458d9147fa200a3c4b4a75bb286efda514276deff0cc8915df02bda0fc07a  java-bonsai-1.0.0.tar.gz')