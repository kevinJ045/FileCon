import os
import subprocess
import re

class Builder():
	def __init__(self, source, class_path, exec_command, lib_path, command, main, cp_command, ext):
		self.source = source
		self.command = command
		self.class_path = class_path
		self.lib_path = lib_path
		self.main = main
		self.cp_command = cp_command
		self.ext = ext
		self.exec_command = exec_command

	def getLibs(self):

		libs_list = os.listdir(self.source+self.lib_path)
		libs = ""
		for lib in libs_list:
			libs += ";"+self.source+self.lib_path+"/"+lib
		return libs

	def compile(self,file,basename):
		if(os.path.splitext(basename)[1] == "."+self.ext):
			print("Compiling: "+basename);
			print("Compiling: "+file);
			# libs = self.getLibs()
			# cmdargs = self.cp_command + ' \\"'+self.source+self.class_path+libs+'\\"'
			# args = [self.command,cmdargs,file]
			# print(args)
			# subprocess.call(self.command+" "+cmdargs + " " + file)

	def start(self):
		self.startBuild(self.source+self.class_path)

	def startBuild(self,dir_):
		dir_ = dir_+"/"
		packages = os.listdir(dir_)
		for package in packages:
			if(os.path.isdir(dir_+package)):
				subpackages = os.listdir(dir_+package)
			else:
				subpackages = []
			for subpackage in subpackages:
				if(os.path.isdir(dir_+package+"/"+subpackage)):
					self.startBuild(dir_+package+"/"+subpackage)
				else:
					self.compile(dir_+package+"/"+subpackage,subpackage)
	def run(self):
		libs = self.getLibs()
		cmdargs = self.cp_command + ' \\"'+self.source+self.class_path+libs+'\\"'
		subprocess.call(self.exec_command +" "+cmdargs+" "+ self.main)
	def run(self, path):
		libs = self.getLibs()
		cmdargs = self.cp_command + ' \\"'+self.source+self.class_path+libs+'\\"'
		subprocess.call(self.exec_command +" "+cmdargs+" "+ path)
