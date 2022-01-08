from pynput.keyboard import Key, Controller


class KeyBoard:

    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):  # Foo 클래스 객체에 _instance 속성이 없다면
            print("__new__ is called\n")
            cls._instance = super().__new__(cls)
        return cls._instance

    def __init__(self):
        cls = type(self)
        if not hasattr(cls, "_init"):  # Foo 클래스 객체에 _init 속성이 없다면
            print("__init__ is called\n")
            self.keyboard = Controller()
            cls._init = True

    def input_key(self, key):
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
