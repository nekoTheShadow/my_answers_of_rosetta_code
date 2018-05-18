"""
Check that file exists: http://rosettacode.org/wiki/Check_that_file_exists
ファイルの存在チェックを行う問題。Python3の場合、いくつか手段があるが、個人的にはpathlib.Path#exists推し。
"""

from pathlib import Path
import unittest

class PathPathlibExistsTest(unittest.TestCase):
    def setUp(self):
        self.existeds = [Path('./input.txt'), Path("./`Abdu'l-Bahá.txt"), Path('./docs')]
        self.existeds[0].touch()
        self.existeds[1].touch()
        self.existeds[2].mkdir()

    def tearDown(self):
        for existed in self.existeds:
            if existed.is_file():
                existed.unlink()
            else:
                existed.rmdir()

    def test_existed_paths(self):
        for existed in self.existeds:
            self.assertTrue(existed.exists())
    
    def test_non_existed_paths(self):
        names = ['/input.txt', '/docs', "/`Abdu'l-Bahá.txt"]
        for name in names:
            self.assertFalse(Path(name).exists())

if __name__ == '__main__':
    unittest.main()

    
