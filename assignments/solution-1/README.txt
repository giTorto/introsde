The available target of the build.xml files can be divided in:
- initialization
     - download-ivy
     - install-ivy
     - resolve
     - init
     - compile (recommended, it has all the dependecies to initialize and then compile the project)
- cleaning
     - clean
- execution (each of this function requires that the ant target compile is executed before)

Selection
   - execute.getHeightByID prints on the console the height related to the given id. To pass the id to ant write
      -DId=X. Where X must be an int. In case of missing passed argument, it will be passed a default value.

   - execute.getWeightByID prints on the console the weight related to the given id. To pass the id to ant write
      -DId=X. Where X must be an int. In case of missing passed argument, it will be passed a default value.

   - execute.getHeightByName prints on the console the height related to the given first name and last name.
      To pass the first name write -DFirstName="somename" and the last name -DLastName="somelastname".
      In case of missing passed argument, it will be passed a default value.

   - execute.getWeightByName prints on the console the weight related to the given first name and last name.
      To pass the first name write -DFirstName="somename" and the last name -DLastName="somelastname".
      In case of missing passed argument, it will be passed a default value.

   - execute.selectOnWeight prints on the console the matching person details based on the given selection string.
     To pass the selection string write -DSelection=">45Kg". If there are more than 20 people
     matching the selection, the result will be paginated. So to see different pages write -DPage=X. Where X
     is the number of a page, as default is setted as 1.
     In case of missing arguments, it will be passed a default value.

   - execute.printAll prints on the console all the people contained in the misses.xml file. If results are more
     than 20 only the first 20 will be shown. To see the next values it's required to pass the page argument.
     To pass the page argument write -DPage=X. Where X is the number of a page, as default page is setted as 1.

   - execute.getHealthProfile prints on the console the health profile related to the given id.
     To pass the id to ant write -DId=X. Where X must be an int.
     In case of missing passed argument, it will be passed a default value.

Marshalling and UnMarshalling

   - execute.MarshallerToJSON marshall some java classes into a JSON file and add also some person taken from
      the misses.xml file(this implies unmarshalling of the misses.xml file). Then print the person on the console
      and creates the people.JSON file.

   - execute.MarshallerToXML marshall some java classes into a XML file and add also some person taken from
      the misses.xml file(this implies unmarshalling of the misses.xml file). Then print the person on the console and creates
      the people.XML file.

   - execute.UnMarshallJSON unmarshall people contained in the people.json file that must be created before with
      execute.MarshallerToJSON. After people is unmarshalled, they are printed on the console with some details.

   - execute.UnMarshallXML unmarshall people contained in the people.xml file that must be created before with
     execute.MarshallerToXML. After people is unmarshalled, they are printed on the console with some details.
     This feature is based on the same of execute.printAll, but in the main is passed as argument people.xml
     instead of misses.xml


