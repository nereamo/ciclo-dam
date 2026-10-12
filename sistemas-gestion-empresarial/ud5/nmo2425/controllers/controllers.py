# -*- coding: utf-8 -*-
# from odoo import http


# class Nmo2425(http.Controller):
#     @http.route('/nmo2425/nmo2425', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/nmo2425/nmo2425/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('nmo2425.listing', {
#             'root': '/nmo2425/nmo2425',
#             'objects': http.request.env['nmo2425.nmo2425'].search([]),
#         })

#     @http.route('/nmo2425/nmo2425/objects/<model("nmo2425.nmo2425"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('nmo2425.object', {
#             'object': obj
#         })

