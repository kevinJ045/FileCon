import builder

kotlin_builder = builder.Builder(
	source = "../SourceKotlin/",
	class_path = 'src',
	lib_path = 'lib',
	main = "MainActivity",
	command = "kotlinc",
	exec_command = "kotlinc",
	cp_command = '-cp',
	ext = 'kt'
)