const client = document.querySelector('.client');
const block = document.querySelector('.block');
const content = document.querySelector('.right-bar');
const toolbar = document.querySelector('.toolbar');
const info = document.querySelector('h3');
const plus = document.querySelector('.plus');
const liste = document.querySelector('.liste');
const body = document.querySelector('body');
const line = document.querySelector('.line');
let page = document.querySelector('.pg, .pg1, .pg2, .page');
const btn = document.querySelector('.btn');
const hr = document.querySelector('.add');
const imageDraggable = document.querySelector('.image');
const tableDraggable = document.querySelector('.tableau');
const container = document.querySelector('.line-containere');
// const dropZone = document.getElementById('drop-zone');
const urlParams = new URLSearchParams(window.location.search);

// Functions

// Fonction pour initialiser les fonctionnalités d'une page
function initializePage(page) {

    // Functions
    const showToolbar = () => {
        toolbar.style.visibility = "visible";
    }
    const hideToolbar = () => {
        toolbar.style.visibility = "hidden";
    }
    
    page.addEventListener('mouseover', function() {
        line.style.visibility = 'visible';
        btn.style.visibility = 'visible';
    })
    
    page.addEventListener('mouseout', function() {
        line.style.visibility = 'hidden';
        btn.style.visibility = 'hidden';
    })
    
    container.addEventListener('mouseover', function() {
        line.style.border = '1px solid #007bff';
        btn.style.color = '#007bff';
        btn.style.border = '2px solid #007bff';
    });
    
    container.addEventListener('mouseout', function() {
        line.style.visibility = 'hidden';
        btn.style.visibility = 'hidden';
        line.style.border = '1px solid black';
        btn.style.color = 'black';
        btn.style.border = '2px solid black';
    });

    page.insertAdjacentHTML("beforeend", '<div class="bloc"><p id="pp" class="pp">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Tenetur cupiditate nisi harum consequuntur debitis eveniet, sint in possimus voluptate alias placeat magnam architecto numquam nihil. Atque impedit quas vero aliquam.</p></div><div class="line-container" style="position: relative; padding: 10px; top: 30px;"><hr class="line-after" style="border: 1px solid black; width: 780px; top: 45px; left: 0%; transition: blue 0.3s;"><button class="btne" style="position: absolute; top: 5px; left: 50%; background-color: white; border: 2px solid black; width: 30px; height: 30px; color: black; cursor: pointer;">+</button></div>');
    //page.insertAdjacentHTML("beforeend", '<div class="line-container" style="position: relative; padding: 10px; top: 30px;"><hr class="line-after" style="border: 1px solid black; width: 780px; top: 45px; left: 0%; transition: blue 0.3s;"><button class="btne" style="position: absolute; top: 5px; left: 50%; background-color: white; border: 2px solid black; width: 30px; height: 30px; color: black; cursor: pointer;">+</button></div>');

    // Ajouter des événements pour les boutons et lignes
    const bloc = page.querySelector('.line-container');
    const ligne = page.querySelector('.line-after');
    const btne = page.querySelector('.btne');
    let textArea = page.querySelector('.bloc');

    textArea.contentEditable = 'true';

    bloc.addEventListener('mouseover', function () {
        ligne.style.visibility = 'visible';
        btne.style.visibility = 'visible';
        ligne.style.border = '1px solid #007bff';
        btne.style.color = '#007bff';
        btne.style.border = '2px solid #007bff';
    });
    bloc.addEventListener('mouseout', function () {
        ligne.style.visibility = 'hidden';
        btne.style.visibility = 'hidden';
        ligne.style.border = '1px solid black';
        btne.style.color = 'black';
        btne.style.border = '2px solid black';
    });

    textArea.addEventListener('focus', function() {
        showToolbar();
    });
    textArea.addEventListener('blur', function() {
        hideToolbar();
    });
    
    // Activer le drag-and-drop pour la page
    initializeDragAndDrop(page);
}

// Fonction pour initialiser le drag-and-drop
function initializeDragAndDrop(page) {
    imageDraggable.addEventListener('dragstart', (e) => {
        const imageSrc = '/img/WhatsApp Image 2024-12-04 at 15.06.49.jpeg';
        e.dataTransfer.setData('image', imageSrc);
    });
    tableDraggable.addEventListener('dragstart', (e) => {
        e.dataTransfer.setData('table', 'table');
    });

    page.addEventListener('dragover', (e) => {
        e.preventDefault();
    });

    page.addEventListener('drop', (e) => {
        e.preventDefault();

        const imageSrc = e.dataTransfer.getData('image');
        if (imageSrc) {
            const container = document.createElement('div');
            container.style.border = '1px solid #ddd';
            container.style.padding = '10px';
            container.style.textAlign = 'center';
            container.style.backgroundColor = '#f9f9f9';
            container.style.borderRadius = '5px';
            container.style.margin = '40px auto';
            container.style.width = '750px';
            container.className = 'bloce';

            container.innerHTML = `
                <img id="img" src="${imageSrc}" alt="Upload Icon" style="display: block; margin: 0 auto; opacity: 0.6; width: 50px;">
                <input type="file" id="fileInput" style="display: none;" accept="image/*">
                <div id="preview" style="text-align: center;">Déposez une image ou cliquez ici</div>
            `;

            page.appendChild(container);
            page.insertAdjacentHTML("beforeend", '<div class="line1-container" style="position: relative; padding: 10px; top: 30px;"><hr class="line-after1" style="border: 1px solid black; width: 780px; top: 45px; left: 0%; transition: blue 0.3s;"><button class="btne1" style="position: absolute; top: 5px; left: 50%; background-color: white; border: 2px solid black; width: 30px; height: 30px; color: black; cursor: pointer;">+</button></div>');


            const btne = page.querySelector('.btne1');
            let bloc = page.querySelector('.line1-container');
            const ligne = page.querySelector('.line-after1');
            const fileInput = container.querySelector('#fileInput');
            const preview = container.querySelector('#preview');
            const img = container.querySelector('#img');

            bloc.addEventListener('mouseover', function() {
                ligne.style.visibility = 'visible';
                btne.style.visibility = 'visible';
                ligne.style.border = '1px solid #007bff';
                btne.style.color = '#007bff';
                btne.style.border = '2px solid #007bff';
            });
            bloc.addEventListener('mouseout', function() {
                ligne.style.visibility = 'hidden';
                btne.style.visibility = 'hidden';
                ligne.style.border = '1px solid black';
                btne.style.color = 'black';
                btne.style.border = '2px solid black';
            });

            preview.addEventListener('click', () => {
                fileInput.click();
            });

            fileInput.addEventListener('change', (event) => {
                const file = event.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = (e) => {
                        container.style.margin = '0';
                        container.style.padding = '0';
                        container.style.color = 'transparent';
                        container.style.border = 'none';
                        preview.innerHTML = `<img src="${e.target.result}" alt="Aperçu" 
                            style="max-width: 100%; position: relative; top: 25px; left: 25px;">`;
                        img.style.display = 'none';
                    };
                    reader.readAsDataURL(file);
                }
            });
        }

        const tableData = e.dataTransfer.getData('table');
        if (tableData) {
            const container = document.createElement('div');
            container.className = 'container';
            container.style.padding = '10px';
            container.style.textAlign = 'center';
            container.style.margin = '25px auto';
            container.style.width = '750px';
            container.style.zIndex = '5';
        
            const tab = `
                <div class="table-container">
                    <table id="dynamic-table" style="width: 100%; border-collapse: collapse;">
                        <thead>
                            <tr class="header-row">
                                <th>Colonne 1</th>
                                <th>Colonne 2</th>
                                <th>Colonne 3</th>
                                <th>Colonne 4</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Donnée 1.1</td>
                                <td>Donnée 1.2</td>
                                <td>Donnée 1.3</td>
                                <td>Donnée 1.4</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            `;

            container.innerHTML = tab;
            page.appendChild(container);
            page.insertAdjacentHTML("beforeend", '<div class="line2-container" style="position: relative; padding: 10px; top: 30px;"><hr class="line-after2" style="border: 1px solid black; width: 780px; top: 45px; left: 0%; transition: blue 0.3s;"><button class="btne2" style="position: absolute; top: 5px; left: 50%; background-color: white; border: 2px solid black; width: 30px; height: 30px; color: black; cursor: pointer;">+</button></div>');

            const btne = page.querySelector('.btne2');
            let bloc = page.querySelector('.line2-container');
            const ligne = page.querySelector('.line-after2');

            bloc.addEventListener('mouseover', function() {
                ligne.style.visibility = 'visible';
                btne.style.visibility = 'visible';
                ligne.style.border = '1px solid #007bff';
                btne.style.color = '#007bff';
                btne.style.border = '2px solid #007bff';
            });
            bloc.addEventListener('mouseout', function() {
                ligne.style.visibility = 'hidden';
                btne.style.visibility = 'hidden';
                ligne.style.border = '1px solid black';
                btne.style.color = 'black';
                btne.style.border = '2px solid black';
            });

            // Initialisation des fonctionnalités
            const table = container.querySelector('#dynamic-table');
            const headerRow = table.querySelector('.header-row');
            
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
        
            // Initialisation des boutons d'ajout
            Array.from(headerRow.cells).forEach(cell => {
                addColumnButton(cell);
            });
        
            Array.from(table.rows).forEach(row => {
                if (row.parentElement.tagName === 'TBODY') {
                    addRowButton(row);
                }
            });
        };
    });
}

// Fonction principale pour ajouter une nouvelle page
plus.addEventListener('click', () => {
    // Ajouter une nouvelle page
    const newPage = document.createElement('div');
    newPage.className = 'page';
    newPage.innerHTML = `
        <page id="pg1" class="pg1" size="A4" style="background: white; position: absolute; width: 21cm; height: 29.7cm; top: 1400px; padding: 30px; margin-bottom: 20px; margin-left: 70px; box-shadow: 0 0 0.5cm rgba(0, 0, 0, 0.5); cursor: pointer;"><div class="line-containere"><hr class="line"><button class="btn">+</button></div></page>`;
    newPage.insertAdjacentHTML("beforeend", '<page id="pg1" class="pg1" size="A4" style="background: white; position: relative; width: 21cm; height: 29.7cm; top: 1400px; padding: 30px; margin-bottom: 20px; margin-left: 70px; box-shadow: 0 0 0.5cm rgba(0, 0, 0, 0.5); cursor: pointer;"><div class="line-containere"><hr class="line"><button class="btn">+</button></div></page>');

    // Initialiser les fonctionnalités pour la nouvelle page
    initializePage(newPage);
});

// Initialiser la première page
initializePage(page);
