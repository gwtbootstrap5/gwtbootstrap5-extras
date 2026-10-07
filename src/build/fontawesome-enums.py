"""Rewrites the constants of the three Font Awesome enums from a fontawesome-free package.

    npm pack @fortawesome/fontawesome-free@X.Y.Z && tar xzf fortawesome-fontawesome-free-X.Y.Z.tgz
    python3 src/build/fontawesome-enums.py package \
        src/main/java/org/gwtbootstrap5/extras/fontawesome/client/ui

The solid and regular enums list every icon of css/fontawesome.css, the brands enum every icon
of css/brands.css (aliases included). Constants are the names in upper case with underscores,
prefixed when they start with a digit: NUMBER_0 last in solid and regular, BRAND_11TY in
alphabetical order in brands.
"""
import os, re, sys

pkg, out = sys.argv[1], sys.argv[2]


def names(css):
    text = open(os.path.join(pkg, 'css', css), encoding='utf-8').read()
    found = []
    for selector, _ in re.findall(r'([^{}]+)\{([^{}]*--fa:[^{}]*)\}', text):
        found += re.findall(r'\.fa-([a-z0-9-]+)', selector)
    return found


def constants(icons, prefix):
    rows = []
    for icon in set(icons):
        constant = icon.upper().replace('-', '_')
        if constant[0].isdigit():
            constant = prefix + constant
        # NUMBER_ constants go last; BRAND_ ones sort by their name
        rows.append((prefix == 'NUMBER_' and icon[0].isdigit(), constant, icon))
    rows.sort(key=lambda r: (r[0], r[1] if r[1].startswith('BRAND_') else r[2].upper().replace('-', '_')))
    return ',\n'.join('    %s("%s")' % (c, i) for _, c, i in rows) + ';'


for enum, css, prefix in (('IconTypeFASolid', 'fontawesome.css', 'NUMBER_'),
                          ('IconTypeFARegular', 'fontawesome.css', 'NUMBER_'),
                          ('IconTypeFABrands', 'brands.css', 'BRAND_')):
    path = os.path.join(out, enum + '.java')
    java = open(path, encoding='utf-8').read()
    start = java.index('implements IconType {\n') + len('implements IconType {\n')
    end = java.index(';', java.index('("', start)) + 1
    open(path, 'w', encoding='utf-8').write(java[:start] + constants(names(css), prefix) + java[end:])
