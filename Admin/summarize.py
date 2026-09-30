# a python script to pull summaries from jamesclear.com/book-summaries

import sys
import html2text


def write(t):
	text = t
	with open("demofile.txt","a+") as f:
		f.write(text)
	print(text)
def main():

	h = html2text.HTML2Text()
	# ignore converting links from html
	h.ignore_links = True
	pass = h.handle("<p>Hello, <a href='https://www.google.com/earth/'>world</a>!")

	write(pass)

if __name__ == '__main__':
  main()


