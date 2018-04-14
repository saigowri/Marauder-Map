from multiprocessing import Process
import sys
import os
import recognize
from time import sleep

def fun1():
	print("started1")
	x=""
	i = 1
	camera = "_1.jpg"
	path = "E:\\Images\\"
	while(True):
		files = os.listdir(path)
		if(len(files) > 0):
			for fname in files:
				if(fname == str(i+1)+camera):
					f = str(i) + camera
					x = path+str(i) + camera
					i+=1
					recognize.recognizeFaces(x, path, f)
					#sleep(2)

def fun2():
	print("started2")
	x=""
	i = 1
	camera = "_2.jpg"
	path = "E:\\Images\\"
	while(True):
		files = os.listdir(path)
		if(len(files) > 0):
			for fname in files:
				if(fname == str(i+1)+camera):
					f = str(i) + camera
					x = path+str(i) + camera
					i+=1
					recognize.recognizeFaces(x, path, f)
					#sleep(2)

if __name__=='__main__':
	recognize.setMarkers()
	p1 = Process(target = fun1)
	p1.start()
	p2 = Process(target = fun2)
	p2.start()