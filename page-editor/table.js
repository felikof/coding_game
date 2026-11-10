function addColumnButton(cell) {
    const button = document.createElement('button');
    button.className = 'add-column-btn';
    button.innerHTML = '<span class="material-icons">add</span>';
    button.title = 'Ajouter une colonne';
    button.onclick = (e) => {
        e.stopPropagation();
        addColumn(cell.cellIndex);
    };
    cell.appendChild(button);
}

function addRowButton(row) {
    const firstCell = row.cells[0];
    const button = document.createElement('button');
    button.className = 'add-row-btn';
    button.innerHTML = '<span class="material-icons">add</span>';
    button.title = 'Ajouter une ligne';
    button.onclick = (e) => {
        e.stopPropagation();
        addRow(row.rowIndex);
    };
    firstCell.appendChild(button);
}

function addColumn(index) {
    const rows = table.rows;
    const columnNumber = headerRow.cells.length + 1;

    // Ajouter la cellule d'en-tête
    const headerCell = headerRow.insertCell(index + 1);
    headerCell.textContent = `Colonne ${columnNumber}`;
    addColumnButton(headerCell);

    // Ajouter les cellules pour chaque ligne
    for (let i = 1; i < rows.length; i++) {
        const cell = rows[i].insertCell(index + 1);
        cell.textContent = `Donnée ${columnNumber}.${i}`;
    }
}

function addRow(index) {
    const newRow = table.tBodies[0].insertRow(index);
    const columnCount = headerRow.cells.length;

    // Ajouter les cellules à la nouvelle ligne
    for (let i = 0; i < columnCount; i++) {
        const cell = newRow.insertCell();
        cell.textContent = `Nouvelle donnée ${i + 1}`;
    }

    addRowButton(newRow);
}

function init(table) {
    const headerRow = table.getElementsByClassName('header-row')[0];
    
    // Ajouter les boutons d'ajout de colonnes aux en-têtes existantes
    Array.from(headerRow.cells).forEach(cell => {
        addColumnButton(cell);
        headerRow.contentEditable = 'true';
    });

    // Ajouter les boutons d'ajout de lignes aux lignes existantes
    Array.from(table.rows).forEach(row => {
        if (row.parentElement.tagName === 'TBODY') {
            addRowButton(row);
        }
        table.contentEditable = 'true';
    });
}

/* document.addEventListener('DOMContentLoaded', () => {
    const table = document.getElementById('dynamic-table');
    const headerRow = document.getElementById('header-row');
    
    // Ajouter les boutons d'ajout de colonnes aux en-têtes existantes
    Array.from(headerRow.cells).forEach(cell => {
        addColumnButton(cell);
        headerRow.contentEditable = 'true';
    });

    // Ajouter les boutons d'ajout de lignes aux lignes existantes
    Array.from(table.rows).forEach(row => {
        if (row.parentElement.tagName === 'TBODY') {
            addRowButton(row);
        }
        table.contentEditable = 'true';
    });
}); */