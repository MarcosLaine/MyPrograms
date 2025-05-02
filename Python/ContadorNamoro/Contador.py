from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.label import Label
from kivy.clock import Clock
from datetime import datetime

class Contador(BoxLayout):
    def __init__(self, **kwargs):
        super(Contador, self).__init__(**kwargs)
        self.orientation = 'vertical'
        
        # Cria um rótulo para exibir o tempo decorrido
        self.label = Label(text="", font_size="24sp", halign="center", valign="middle")
        # Necessário para que o alinhamento vertical funcione corretamente
        self.label.bind(size=self.label.setter('text_size'))
        self.add_widget(self.label)
        
        # Agenda a atualização do contador a cada 1 segundo
        Clock.schedule_interval(self.atualizar_contador, 1)

    def atualizar_contador(self, dt):
        # Data inicial: 13 de dezembro de 2023, 19:30:00
        data_inicial = datetime(2023, 12, 13, 19, 30, 0)
        agora = datetime.now()
        delta = agora - data_inicial
        
        # Calcula dias, horas, minutos e segundos
        dias = delta.days
        horas, resto = divmod(delta.seconds, 3600)
        minutos, segundos = divmod(resto, 60)
        
        # Atualiza o texto do rótulo com o tempo decorrido
        self.label.text = (f"{dias} dias, {horas:02d} horas, "
                           f"{minutos:02d} minutos, {segundos:02d} segundos")

class ContadorApp(App):
    def build(self):
        self.title = "Contador - Tempo Juntos"
        return Contador()

if __name__ == "__main__":
    ContadorApp().run()
