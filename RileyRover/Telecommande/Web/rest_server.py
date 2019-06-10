#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from tornado import websocket, web, ioloop

class TelecommandeHandler(web.RequestHandler):
    def initialize(self, *args, **kwargs):
        self.remote_ip = self.request.headers.get('X-Forwarded-For', self.request.headers.get('X-Real-Ip', self.request.remote_ip))
    def get(self, *args):
        print('connection ! from : '+self.remote_ip)
        self.render("./telecommande.html")

class DevTelecommandeHandler(TelecommandeHandler):
    def get(self, *args):
        print('connection ! from : '+self.remote_ip)
        self.render("./telecommandeV2.html")

app = web.Application([
    (r'/telecommande', TelecommandeHandler),
    (r'/telecommandedev', DevTelecommandeHandler),
    (r"/ICONS/(.*)", web.StaticFileHandler, {"path": "./ICONS/"}),
])

if __name__ == '__main__':
    app.listen(7727)
    ioloop.IOLoop.instance().start()
