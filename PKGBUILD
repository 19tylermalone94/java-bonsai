# Maintainer: Tyler Malone <github.com/19tylermalone94>

pkgname=java-bonsai
pkgver=1.0.0
pkgrel=1
pkgdesc="A program for generating pixel bonsai trees written in Java."
arch=('any')
url="https://github.com/19tylermalone94/java-bonsai"
license=('MIT')
depends=('java-runtime')

source=("https://github.com/19tylermalone94/java-bonsai/releases/download/v${pkgver}/${pkgname}-${pkgver}.tar.gz", ""https://github.com/19tylermalone94/java-bonsai/releases/download/v${pkgver}/setup.sh")

package() {
  cd "$srcdir"
  mkdir -p "${pkgdir}/usr/share/java-bonsai"
  cp -r * "${pkgdir}/usr/share/java-bonsai"
  chmod +x "${pkgdir}/usr/share/java-bonsai/bin/java-bonsai.sh"
  ln -sf "$srcdir/$pkgname-$pkgver/bin/java-bonsai.sh" "$pkgdir/usr/share/java-bonsai/bin/java-bonsai.sh"
  install -Dm755 "${pkgdir}/setup.sh" "${pkgdir}/usr/share/java-bonsai/bin/setup.sh"
}

sha256sums=('e14458d9147fa200a3c4b4a75bb286efda514276deff0cc8915df02bda0fc07a')

