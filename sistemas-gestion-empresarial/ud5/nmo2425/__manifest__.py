# -*- coding: utf-8 -*-
{
    'name': "Veterinarian",

    'summary': "Manage pets, appointments and veterinarians",

    'description': """
Long description of module's purpose
    """,

    'author': "Nerea Montoya",
    'website': "https://www.yourcompany.com",

    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/15.0/odoo/addons/base/data/ir_module_category_data.xml
    # for the full list
    'category': 'Services',
    'version': '1.0',

    # any module necessary for this one to work correctly
    'depends': ['base', 'hr'],

    # always loaded
    'data': [
        'security/ir.model.access.csv',
        'views/views.xml',
        'views/templates.xml',
        'data/veterinarian_data.xml',
        'data/pet_data.xml',
        'data/appointment_data.xml',
    ],
    'images': ['static/description/icon.png'],
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
    ],

    'application': True,
    'installable': True,  
}

