from enum import Enum, unique

from pubsub import pub


@unique
class Event(Enum):
    KeyPress = 'keyPress'


def subscribe(callable, event: Event):
    pub.subscribe(callable, event.value)


def send_message(event: Event, **data):
    pub.sendMessage(event.value, **data)
