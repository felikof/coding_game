const client = document.querySelector('.client');
const block = document.querySelector('.block');
const content = document.querySelector('.right-bar');
const toolbar = document.querySelector('.toolbar');
const info = document.querySelector('h3');
const plus = document.querySelector('.plus');
const liste = document.querySelector('.liste');
const body = document.querySelector('body');
const line = document.querySelector('.line');
let pg = document.querySelector('.pg1, .pg');
const btn = document.querySelector('.btn');
const hr = document.querySelector('.add');
const imageDraggable = document.querySelector('.image');
const tableDraggable = document.querySelector('.tableau');
const container = document.querySelector('.line-containere');
// const dropZone = document.getElementById('drop-zone');
const urlParams = new URLSearchParams(window.location.search);

// Functions
function initPg(page) {
    const showToolbar = () => {
        toolbar.style.visibility = "visible";
    }
    const hideToolbar = () => {
        toolbar.style.visibility = "hidden";
    }
    
    pg.addEventListener('mouseover', function() {
        line.style.visibility = 'visible';
        btn.style.visibility = 'visible';
    })
    
    pg.addEventListener('mouseout', function() {
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
    
    const textArea = () => {
        pg.insertAdjacentHTML("beforeend", '<div class="bloc"><p id="pp" class="pp">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Tenetur cupiditate nisi harum consequuntur debitis eveniet, sint in possimus voluptate alias placeat magnam architecto numquam nihil. Atque impedit quas vero aliquam.</p></div>');
        pg.insertAdjacentHTML("beforeend", '<div class="line-container" style="position: relative; padding: 10px; top: 30px;"><hr class="line-after" style="border: 1px solid black; width: 780px; top: 45px; left: 0%; transition: blue 0.3s;"><button class="btne" style="position: absolute; top: 5px; left: 50%; background-color: white; border: 2px solid black; width: 30px; height: 30px; color: black; cursor: pointer;">+</button></div>');
    
        const ligne = pg.querySelector('.line-after');
        const btne = pg.querySelector('.btne');
        let bloc = pg.querySelector('.line-container');
        let textArea = pg.querySelector('.bloc');
        const maxHeight = 5 * 20; // 5 cm
    
        btne.style.visibility = 'visible';
        ligne.style.visibility = 'visible';
        //btne.style.visibility = 'hidden';
        //ligne.style.visibility = 'hidden';
        textArea.contentEditable = 'true';
    
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
        if (bloc.scrollHeight === maxHeight) {                                                  
            bloc.contentEditable = 'false';
            ligne.remove();
            btne.remove();
        }
    
        textArea.addEventListener('focus', function() {
            showToolbar();
        });
        textArea.addEventListener('blur', function() {
            hideToolbar();
        });
    }
    
    // Main
    plus.addEventListener("click", (e) => {
        body.insertAdjacentHTML("beforeend" ,'<page id="pg1" class="pg1" size="A4" style="position: relative; left: 0px; right: 100px; bottom: 500px; text-align: left; background-color: white;"><div class="line-containere"><hr class="line"><button class="btn">+</button></div></page>');
        const btn = document.querySelector('.btn');
        btn.addEventListener('click', textArea);
    });
    
    pg.addEventListener('mouseover', function() {
        plus.style.visibility = 'hidden';
    });
    pg.addEventListener('mouseout', function() {
        plus.style.visibility = 'visible';
    });
    
    btn.addEventListener('click', textArea);
    dragDrop(page);
}


// Drag Drop
/* draggable.addEventListener('dragstart', (e) => {
    const imageSrc = 'https://thumbs.dreamstime.com/b/beau-d-paysage-automne-avec-des-arbres-et-feuilles-au-soleil-en-ecosse-124278811.jpg';
    e.dataTransfer.setData('text/plain', imageSrc);
    // console.log("DragDrop");
})

pg.addEventListener('dragover', (e) => {
    e.preventDefault();
})

// Lors du dépôt
pg.addEventListener('drop', (e) => {
    e.preventDefault();
    const imageSrc = e.dataTransfer.getData('text/plain');
    //const imageSrc = 'https://thumbs.dreamstime.com/b/beau-d-paysage-automne-avec-des-arbres-et-feuilles-au-soleil-en-ecosse-124278811.jpg';

    const img = document.createElement('img');
    img.src = imageSrc;

    pg.appendChild(img);
}) */

// Gestion du drag-and-drop
function dragDrop(page) {
    imageDraggable.addEventListener('dragstart', (e) => {
        const imageSrc = '/img/WhatsApp Image 2024-12-04 at 15.06.49.jpeg';
        e.dataTransfer.setData('image', imageSrc);
    });
    tableDraggable.addEventListener('dragstart', (e) => {
        e.dataTransfer.setData('table', 'table');
    });
    
    pg.addEventListener('dragover', (e) => {
        e.preventDefault();
    });
    
    // Lorsqu'une image est déposée
    pg.addEventListener('drop', (e) => {
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
    
            pg.appendChild(container);
            pg.insertAdjacentHTML("beforeend", '<div class="line1-container" style="position: relative; padding: 10px; top: 30px;"><hr class="line-after1" style="border: 1px solid black; width: 780px; top: 45px; left: 0%; transition: blue 0.3s;"><button class="btne1" style="position: absolute; top: 5px; left: 50%; background-color: white; border: 2px solid black; width: 30px; height: 30px; color: black; cursor: pointer;">+</button></div>');
    
    
            const btne = pg.querySelector('.btne1');
            let bloc = pg.querySelector('.line1-container');
            const ligne = pg.querySelector('.line-after1');
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
            container.style.zIndex = '10';
        
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
            pg.appendChild(container);
            pg.insertAdjacentHTML("beforeend", '<div class="line2-container" style="position: relative; padding: 10px; top: 30px;"><hr class="line-after2" style="border: 1px solid black; width: 780px; top: 45px; left: 0%; transition: blue 0.3s;"><button class="btne2" style="position: absolute; top: 5px; left: 50%; background-color: white; border: 2px solid black; width: 30px; height: 30px; color: black; cursor: pointer;">+</button></div>');
    
            const btne = pg.querySelector('.btne2');
            let bloc = pg.querySelector('.line2-container');
            const ligne = pg.querySelector('.line-after2');
    
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

plus.addEventListener('click', () => {
    // Ajouter une nouvelle page
    const newPage = document.createElement('div');
    newPage.className = 'page';
    newPage.innerHTML = `
        <page id="pg1" class="pg1" size="A4" style="background: white; position: absolute; width: 21cm; height: 29.7cm; top: 1400px; padding: 30px; margin-bottom: 20px; margin-left: 70px; box-shadow: 0 0 0.5cm rgba(0, 0, 0, 0.5); cursor: pointer;"><div class="line-containere"><hr class="line"><button class="btn">+</button></div></page>`;
    newPage.insertAdjacentHTML("beforeend", '<page id="pg1" class="pg1" size="A4" style="background: white; position: relative; width: 21cm; height: 29.7cm; top: 1400px; padding: 30px; margin-bottom: 20px; margin-left: 70px; box-shadow: 0 0 0.5cm rgba(0, 0, 0, 0.5); cursor: pointer;"><div class="line-containere"><hr class="line"><button class="btn">+</button></div></page>');

    // Initialiser les fonctionnalités pour la nouvelle page
    initPg(newPage);
});


    /* if (tableData) {
        // Gestion du tableau
        let data = [['']];

        const generateTable = () => {
            let tableHTML = '<table id="dynamic-tablee" style="width: 100%; border-collapse: collapse;">';

            data.forEach((row, rowIndex) => {
                tableHTML += '<tr class="header-row">';
                row.forEach((cell, colIndex) => {
                    if (rowIndex === 0) {
                        tableHTML += `<th style="border: 1px solid #ddd; padding: 8px; background-color: #f2f2f2;" contenteditable="true">${cell}</th>`;
                    } else {
                        tableHTML += `<td style="border: 1px solid #ddd; padding: 8px;" contenteditable="true">${cell}</td>`;
                    }
                });
                tableHTML += '</tr>';
            });

            tableHTML += '</table>';
            return tableHTML;
        };

        const container = document.createElement('div');
        container.style.padding = '10px';
        container.style.textAlign = 'center';
        container.style.backgroundColor = '#f9f9f9';
        container.style.margin = '25px auto';
        container.style.width = '750px';
        container.className = 'blocee';

        const controlsHTML = `
            <div class="modif" style="margin-bottom: 10px;">
                <button id="addRow" style="margin-right: 5px;">Ajouter une ligne</button>
                <button id="addColumn" style="margin-right: 5px;">Ajouter une colonne</button>
                <button id="removeRow" style="margin-right: 5px;">Supprimer une ligne</button>
                <button id="removeColumn">Supprimer une colonne</button>
            </div>
        `;

        container.innerHTML = controlsHTML + generateTable();
        pg.appendChild(container);
        pg.insertAdjacentHTML("beforeend", '<hr class="line-after" style="border: 1px solid #007bff; width: 780px; position: relative; top: 45px; left: 0%; transition: blue 0.3s;"><button class="btnee" style="position: relative; top: 22px; left: 50%; background-color: white; border: 2px solid #007bff; width: 30px; height: 30px; color: #007bff; cursor: pointer;">+</button>');

        const ligne = pg.querySelector('.line-after');
        const btn = pg.querySelector('.btnee');

        const addRowButton = container.querySelector('#addRow');
        const addColumnButton = container.querySelector('#addColumn');
        const removeRowButton = container.querySelector('#removeRow');
        const removeColumnButton = container.querySelector('#removeColumn');

        addRowButton.addEventListener('click', () => {
            const newRow = Array(data[0].length).fill('');
            data.push(newRow);
            container.querySelector('#dynamic-tablee').outerHTML = generateTable();
        });

        addColumnButton.addEventListener('click', () => {
            data.forEach(row => row.push(''));
            container.querySelector('#dynamic-tablee').outerHTML = generateTable();
        });

        removeRowButton.addEventListener('click', () => {
            if (data.length > 1) {
                data.pop();
                container.querySelector('#dynamic-tablee').outerHTML = generateTable();
            } else {
                alert("Impossible de supprimer toutes les lignes !");
            }
        });

        removeColumnButton.addEventListener('click', () => {
            if (data[0].length > 1) {
                data.forEach(row => row.pop());
                container.querySelector('#dynamic-tablee').outerHTML = generateTable();
            } else {
                alert("Impossible de supprimer toutes les colonnes !");
            }
        });
    } */
    /* const tableData = e.dataTransfer.getData('table');
    if (tableData) {
        const container = document.createElement('div');
        container.className = 'container';
        container.style.padding = '10px';
        container.style.textAlign = 'center';
        container.style.backgroundColor = '#f9f9f9';
        container.style.margin = '25px auto';
        container.style.width = '750px';
    
        container.innerHTML = `
            <div class="table-container">
                <table id="dynamic-table" style="width: 100%; border-collapse: collapse;">
                    <thead>
                        <tr class="header-row">
                            <th>Colonne 1</th>
                            <th>Colonne 2</th>
                            <th>Colonne 3</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Donnée 1.1</td>
                            <td>Donnée 1.2</td>
                            <td>Donnée 1.3</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        `;
    
        pg.appendChild(container);
    
        // Initialisation des fonctionnalités
        const table = container.querySelector('#dynamic-table');
        const headerRow = table.querySelector('.header-row');
    
        function addColumn(index) {
            const rows = table.rows;
            const columnNumber = headerRow.cells.length + 1;
    
            // Ajouter la cellule d'en-tête
            const headerCell = headerRow.insertCell(index + 1);
            headerCell.textContent = Colonne ${columnNumber};
            addColumnButton(headerCell);
    
            // Ajouter les cellules pour chaque ligne
            for (let i = 1; i < rows.length; i++) {
                const cell = rows[i].insertCell(index + 1);
                cell.textContent = Donnée ${columnNumber}.${i};
            }
        }
    
        function addRow(index) {
            const newRow = table.tBodies[0].insertRow(index);
            const columnCount = headerRow.cells.length;
    
            // Ajouter les cellules à la nouvelle ligne
            for (let i = 0; i < columnCount; i++) {
                const cell = newRow.insertCell();
                cell.textContent = Nouvelle donnée ${i + 1};
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
    } */

/* if (urlParams.get('preview') === 'true') {
    body .style.margin = '20px 30px 50px 30px';
    body.style.backgroundColor = 'lightgrey';
    body.innerHTML = `
        <page class="page" size="A4" dropzone="A4" style="box-shadow: none">
            <div><b><u>Article 7</u>: Clause of responsibility</b></div>
            <div><br></div>
            <div>The Consultant responsibility is involved only for the declarations agreed and submitted by the
                Consultant on the behalf of the Client.</div>
            <div><br></div>
            <div>The Consultant guarantees all Income Tax Returns prepared during the statute of limitations (three
                years) if there are no changes to the Tax return prepared.&nbsp;</div>
            <div><br></div>
            <div>The Client is responsible for all information provided.</div>
            <div><br></div>
            <div>The Client is responsible for the submission of the tax return and for complying with the relevant
                deadlines, unless 4.2 and 4.3 applies.</div>
            <div><br></div>
            <div>The Consultant disclaims all responsibility concerning both the answers provided in the questionnaire
                and any, and all modifications made after the tax return is prepared.&nbsp;</div>
            <div><br></div>
            <div>The fees proposed by the Consultant and agreed by the Client for the Income Tax Return
                service includes:</div>
            <div><br></div>
            <div>The preparation of the Income Tax Return</div>
            <div>The submission of the Income Tax Return to the French Tax Administration (FTA) on behalf of the Client,
                if applicable&nbsp;</div>
            <div>The guarantee of the tax returns provided to the Client during the statute of limitation (three years)
                if there are no changes to the Income Tax Return prepared by the Consultant.</div>
            <div>If a tax return must be amended due to one of these three reasons:</div>
            <div>a mistake or error made by the FTA for a tax return correctly submitted by ELITAX,&nbsp;</div>
            <div>information not provided by the Client in the Income Tax Return Questionnaire,</div>
            <div>the fact that the Client uses a tax return previously provided to him/her by the Consultant as basis
                for any future Income Tax Return filing(s),</div>
            <div><br></div>
            <div><i><b>The amendment will not be supported by the Consultant.</b></i></div>
            <div><i><b>If the Consultant assists the Client in the preparation of this amended Income Tax Return,
                        additional fees will be applied.</b></i></div>
            <div>If a mistake or error is made by the FTA in the calculations of the income tax to pay, concerning a tax
                return correctly submitted by ELITAX, and the Client needs ELITAX's assistance,&nbsp; <b><i>additional
                        fees will be applied.</i></b></div>
        </page>
    `;
} */