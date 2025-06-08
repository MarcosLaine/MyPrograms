import nbformat as nbf
import re

def convert_py_to_ipynb(py_file, ipynb_file):
    # Read the Python file with UTF-8 encoding
    with open(py_file, 'r', encoding='utf-8') as f:
        content = f.read()

    # Create a new notebook
    nb = nbf.v4.new_notebook()

    # Split the content into cells
    # First, split by comments that look like section headers
    sections = re.split(r'# ={3,}\n# ([^\n]+)\n# ={3,}', content)
    
    cells = []
    for i, section in enumerate(sections):
        if i == 0:  # First section is usually imports
            cells.append(nbf.v4.new_code_cell(section.strip()))
        else:
            # Add a markdown cell for the section header
            header = re.search(r'# ={3,}\n# ([^\n]+)\n# ={3,}', content).group(1)
            cells.append(nbf.v4.new_markdown_cell(f"## {header}"))
            # Add the code cell
            cells.append(nbf.v4.new_code_cell(section.strip()))

    nb['cells'] = cells

    # Write the notebook to a file
    with open(ipynb_file, 'w', encoding='utf-8') as f:
        nbf.write(nb, f)

if __name__ == '__main__':
    convert_py_to_ipynb('pipeline_titanic.py', 'pipeline_titanic.ipynb') 