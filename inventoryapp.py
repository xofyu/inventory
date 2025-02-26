from kivymd.app import MDApp
from kivy.uix.screenmanager import Screen
from kivy.long import Builder

KV = '''
MDScreen:
	MDRectangleFlatButton:
		text: "testing..1..22..3""
'''

class MainApp(MDApp):
	def __init__(self):
		super().__init__()
		self.kvs = Builder.load_string(KV)

	def build(self):
		screen = Screen()
		screen.add_widget(self.kvs)
		return screen


ma = MainApp()
ma.run()