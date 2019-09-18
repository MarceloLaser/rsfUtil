# rsfUtil

Utility package for handling RSF files generated by ARCADE.

## Usage disclaimer

rsfUtil does not include any exception handling. It assumes that all arguments are correct and that the utility will be used correctly. Therefore, misuse may cause unexpected errors.

## Arguments

`--outputType`: May be `dot` or `json`. Case insensitive.

`--inputPath`: Location of the .rsf input file.

`--outputPath`: Fully-qualified name of output file to be created.

`--substring`: Substring for Substring Filter.

`--subsetPath`: Path to crawl from for Subset Filter.

`--prepend`: String to prepend to all entries of the Subset Filter.

## Filters

`Substring Filter`: Requires the `--substring` argument. Will filter out all entities from the input .rsf file that do not contain the specified substring.

`Subset Filter`: Requires the `--subsetPath` and `--prepend` arguments. Will crawl through a directory structure starting at `--subsetPath` and list the fully qualified names of all files. Will then filter in from the input .rsf file only those entities that match the names obtained from `--subsetPath`. `--prepend` adds a `String` before the name of each entry in the filter list.

## Example

`java -jar rsfutil-1.0.1.jar --outputType json --inputPath ~/arcade/arcade-1.0_r1_acdc_clustered.rsf --outputPath ~/arcade/arcade_eva.json --subsetPath ~/arcade/src/edu/usc/softarch --prepend edu.usc`
