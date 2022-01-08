from soonyong import event
from soonyong.event import Event
from soonyong.keyboard import KeyBoard


def main():
    keyboard = KeyBoard()

    event.subscribe(keyboard.press, Event.KeyPress)
    event.send_message(Event.KeyPress, key='k')


if __name__ == '__main__':
    main()
