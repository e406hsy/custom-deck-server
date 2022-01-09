from enum import Enum, unique
from typing import Callable

from pubsub import pub


@unique
class Event(Enum):
    KeyPress = 'keyPress'


def subscribe(func: Callable, event: Event) -> None:
    pub.subscribe(func, event.value)


def send_message(event: Event, **data):
    pub.sendMessage(event.value, **data)
