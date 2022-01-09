from pynput.keyboard import Key, Controller


class KeyBoard:

    def __init__(self):
        self.keyboard = Controller()

    def input_key(self, key: str):
        self.keyboard.press(key)
        self.keyboard.release(key)

    def press(self, key):
        print('press called')
        self.keyboard.press(key)

    def release(self, key):
        self.keyboard.release(key)

    def input_key_with_shift(self, key):
        with self.keyboard.pressed(Key.shift):
            self.keyboard.press(key)
            self.keyboard.release(key)

    def input_key_with_control(self, key):
        with self.keyboard.pressed(Key.ctrl):
            self.keyboard.press(key)
            self.keyboard.release(key)

    def input_key_with(self, key, *with_key):
        with self.keyboard.pressed(*with_key):
            self.keyboard.press(key)
            self.keyboard.release(key)

    def type_string(self, string):
        self.keyboard.type(string)


if __name__ == '__main__':
    board = KeyBoard()
