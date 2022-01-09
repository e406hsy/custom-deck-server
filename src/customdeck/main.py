from customdeck import event
from customdeck.event import Event
from customdeck.keyboard import KeyBoard


def main():
    keyboard = KeyBoard()

    event.subscribe(keyboard.press, Event.KeyPress)
    event.send_message(Event.KeyPress, key='k')


if __name__ == '__main__':
    main()
