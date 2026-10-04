import re

with open('src/main/java/snownee/snow/datagen/SRMBlockTagsProvider.java', 'r') as f:
    text = f.read()

text = text.replace('valueLookupBuilder', 'tag')

def replacer(match):
    args = match.group(1).split(',')
    new_args = [arg.strip() + '.builtInRegistryHolder().key()' for arg in args]
    return '.add(' + ', '.join(new_args) + ')'

text = re.sub(r'\.add\((.*?)\)', replacer, text)

with open('src/main/java/snownee/snow/datagen/SRMBlockTagsProvider.java', 'w') as f:
    f.write(text)
