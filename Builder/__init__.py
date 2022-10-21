import os
import subprocess
import re

class Builder():
	def __init__(self, source, class_path, exec_command, bin_folder, lib_path, command, main, cp_command, ext, exec_command_run):
		self.source = source
		self.command = command
		self.class_path = class_path
		self.lib_path = lib_path
		self.main = main
		self.cp_command = cp_command
		self.bin_folder = bin_folder
		self.ext = ext
		self.exec_command = exec_command
		self.exec_command_run = exec_command_run

	def getLibs(self):

		libs_list = os.listdir(self.source+self.lib_path)
		libs = ""
		for lib in libs_list:
			libs += ";"+self.source+self.lib_path+"/"+lib
		return libs

	def compile(self,file,basename):
		if(os.path.splitext(basename)[1] == "."+self.ext):
			print("Compiling: "+basename);
			libs = self.getLibs()
			cmdargs = self.cp_command + ' \\"'+self.source+self.bin_folder+libs+'\\"'
			subprocess.call(self.command+" "+cmdargs + " " +" -d \""+self.source+self.bin_folder+"\" " + file)

	def start(self):
		self.startBuild(self.source+self.class_path)

	def startBuild(self,dir_):
		dir_ = dir_+"/"
		packages = os.listdir(dir_)
		for package in packages:
			if(os.path.isdir(dir_+package)):
				subpackages = os.listdir(dir_+package)
			else:
				self.compile(dir_+package,package)
				subpackages = []
			for subpackage in subpackages:
				if(os.path.isdir(dir_+package+"/"+subpackage)):
					self.startBuild(dir_+package+"/"+subpackage)
				else:
					self.compile(dir_+package+"/"+subpackage,subpackage)
	def run(self):
		libs = self.getLibs()
		cmdargs = self.cp_command + ' \\"'+self.source+self.bin_folder+libs+'\\"'
		subprocess.call(self.exec_command_run +" "+cmdargs+" "+ self.main)
	def run(self, path):
		libs = self.getLibs()
		cmdargs = self.cp_command + ' \\"'+self.source+self.bin_folder+libs+'\\"'
		c_d = self.source+self.bin_folder
		# print(c_d+self.exec_command_run +" "+cmdargs+" "+ path)
		# subprocess.call(["echo","%CD%"])
		subprocess.call(self.exec_command_run +" "+cmdargs+" "+ path)


java_builder = Builder(
	source = "Source/",
	class_path = 'src',
	lib_path = 'lib',
	main = "com/srch/MainActivity",
	command = "javac",
	exec_command = "java",
	cp_command = '-cp',
	bin_folder = "bin",
	ext = 'java',
	exec_command_run = "Builder\\runjava.cmd"
)

# def jar(classp,src):
# 	subprocess.call("Builder\\makejar.cmd "+ classp + " ../out/FileCon.jar "+src)
# java_builder.jar = jar
# def javarun(self):
# 	libs = self.getLibs()
# 	cmdargs = self.cp_command + ' \\"'+self.source+self.class_path+libs+'\\"'
# 	subprocess.call("Builder\\runjar.cmd "+cmdargs+" Source/out FileCon.jar")
# 	# subprocess.call("java -jar "+cmdargs+" Source/out FileCon.jar")
# java_builder.run = javarun

kotlin_builder = Builder(
	source = "SourceKotlin/",
	class_path = 'src',
	lib_path = 'lib',
	main = "MainActivity",
	command = "kotlinc",
	exec_command_run = "kotlinc",
	exec_command = "kotlinc",
	cp_command = '-cp',
	bin_folder = "bin",
	ext = 'kt'
)
