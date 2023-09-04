import argparse
from pypdf import PdfReader, PdfWriter

def main() -> int:
    parser = argparse.ArgumentParser(prog='pythreader')
    parser.add_argument('files', type=str, nargs='+')
    args = parser.parse_args()
    
    merger = PdfWriter()
    remover = PdfWriter()

    for file in args.files:
        merger.append(file)

    merger.write('result.pdf')
    merger.close()

    reader = PdfReader('result.pdf')
    numpages = len(reader.pages)
    pagerange = '1-{}'.format(numpages)
    prompt = 'Pages to remove (comma-separated numbers from {}): '.format(pagerange)
    prompt = input(prompt)
    pagestoremove = []

    for page in prompt.split(','):
        if not page.isdigit():
            print("'{}' is not a valid page number".format(page))
            return 1
            
        idx = int(page) - 1
        if idx >= numpages or idx < 0:
            print("'{}' is out of range {}".format(page, pagerange))
            return 1

        pagestoremove.append(idx)

    for i in range(numpages):
        if i in pagestoremove:
            continue

        page = reader.pages[i]
        remover.add_page(page)

    remover.write('result-removed.pdf')
    remover.close()

    return 0

if __name__ == '__main__':
    main()