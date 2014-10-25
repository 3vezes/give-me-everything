# give-me-everything

#### Problems
- Image quality is default imageIO quality, need to change to higher compression quality.
- Need to add options.

## Description
Java program downloads all images from a url to disk

Usage: give-me-everything url location-to-save-to

## Instructions
####Manual Install
\* ant is required to compile project

3. Move the compiled jar file in bin/jar to preferred location. (you can recompile using ant if you want, or simply used the compiled jar in the project dir)
4. Move give-me-everything.sh to your dotfiles folder.
5. Change CLASSPATH in give-me-everything.sh to the location where you'll be storing the jar file.
5. Source give-me-everything.sh in your rc file.
6. Done.

####Lazy Install

1. Run the install file <code>./install</code> this will move the compiled jar and give-me-everything.sh into your home directory into ~/.give-me-everything
2. You still have to source the file in your dotfiles, add <code>source ~/give-me-everything/give-me-everything.sh</code>