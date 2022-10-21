import builder

java_builder = builder.Builder(
	source = "../Source/",
	class_path = 'src',
	lib_path = 'lib',
	main = "com/srch/MainActivity",
	command = "javac",
	exec_command = "java",
	cp_command = '-cp',
	ext = 'java'
)