# a python script to pull summaries from jamesclear.com/book-summaries

import sys
import html2text
import requests

def getSomeText():
	url = "https://jamesclear.com/book-summaries"
	reponse = requests.get(url)
	
	h = html2text.HTML2Text()
	h.ignore_links = True
	h.ignore_images = True
	h.ignore_emphasis = True
	dl_html = "<p>Hello, <a href='https://www.google.com/earth/'>world</a>!"
	clean_text = h.handle(dl_html).strip()

	return clean_text

def write(t):
	text = t
	with open("demofile.txt","a") as f:
		f.write(t)
	print(text)
def main():
	raw_html = getSomeText()
	text = html2text.html2text(raw_html)
	write(text)

if __name__ == '__main__':
  main()


