# a python script to pull summaries from jamesclear.com/book-summaries

import sys
import html2text

def main():

	h = html2text.HTML2Text()
	# ignore converting links from html
	h.ignore_links = True
	print(h.handle("<p>Hello, <a href='https://www.google.com/earth/'>world</a>!"))

if __name__ == '__main__':
  main()


