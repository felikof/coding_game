import asyncio
from pyppeteer import launch

async def generate_pdf():
    browser = await launch({
        'headless': True,
        'executablePath': '/Applications/Google Chrome.app/Contents/MacOS/Google Chrome'
    })
    page = await browser.newPage()

    await page.goto('file:///Users/v12/Documents/felix-exos/felix-exos/page-editor/index.html?preview=true', {'waitUntil': 'networkidle0'})

    # Sélectionner l'élément contenant le texte
    await page.waitForSelector('.bloc', {'visible': True, 'timeout': 60000})  # Remplacez par l'ID ou la classe de votre élément

    element = await page.querySelector('.bloc')

    # content = await page.content()
    content = await page.waitForXPath('//*[@class="bloc"]')

    print(content)  # Cela imprime le HTML de la page dans la console

    if element:
        box = await element.boundingBox()
        # Générer le PDF avec la zone spécifiée
        await page.pdf({
            'path': 'exemple105.pdf',
            'format': 'A4',
            'printBackground': False,
            'margin': {
                'top': '1cm',
                'right': '1cm',
                'bottom': '1cm',
                'left': '1cm'
            },
            'clip': {
                'x': box['x'],  # Position horizontale de l'élément
                'y': box['y'],  # Position verticale de l'élément
                'width': box['width'],  # Largeur de l'élément
                'height': box['height']  # Hauteur de l'élément
            }
        })

    # await page.waitForSelector('.pp', {'timeout': 10000})
    # clip_area = await page.evaluate('''() => {
    #     const element = document.getElementById('#pp');
    #     if (!element) return null;
        
    #     const { x, y, width, height } = element.getBoundingClientRect();
    #     console.log(x, y, width, height);
    #     return { x, y, width, height };
    # }''')

    # if clip_area:
    #     await page.pdf({
    #         'path': 'exemple03_partie.pdf',
    #         'printBackground': False,
    #         'margin': {
    #             'top': '1cm',
    #             'right': '1cm',
    #             'bottom': '1cm',
    #             'left': '1cm'
    #         },
    #         'clip': clip_area  # Utilise les dimensions obtenues pour capturer uniquement la région de l'élément
    #     })
    # else:
    #     print("Élément non trouvé. Vérifiez l'ID de l'élément.")

    await browser.close()

# Lancer la fonction
asyncio.get_event_loop().run_until_complete(generate_pdf())