import face_recognition
from PIL import Image, ImageDraw, ImageFont

known_face_encodings = []
known_face_names = []

def setMarkers():
    aman_image = face_recognition.load_image_file("aman.jpg")
    known_face_encodings.append(face_recognition.face_encodings(aman_image)[0])
    known_face_names.append("Aman Jain")
    
    gowri_image = face_recognition.load_image_file("gowri.jpg")
    known_face_encodings.append(face_recognition.face_encodings(gowri_image)[0])
    known_face_names.append("Sai Gowri")
    
    vivek_image = face_recognition.load_image_file("vivek.jpg")
    known_face_encodings.append(face_recognition.face_encodings(vivek_image)[0])
    known_face_names.append("Vivek Talreja")

def getMarkers():
	
	if(len(known_face_names) == 0):
		setMarkers()

	return known_face_encodings, known_face_names

def recognizeFaces(x, path, fname):
	getMarkers()
	unknown_image = face_recognition.load_image_file(x)

	face_locations = face_recognition.face_locations(unknown_image)
	face_encodings = face_recognition.face_encodings(unknown_image, face_locations)
	pil_image = Image.fromarray(unknown_image)
	draw = ImageDraw.Draw(pil_image)
	name = "Unknown"
	flag = False
	for (top, right, bottom, left), face_encoding in zip(face_locations, face_encodings):
	    flag = True
	    matches = face_recognition.compare_faces(known_face_encodings, face_encoding, 0.5)
	    name = "Unknown"
	    if True in matches:
	        first_match_index = matches.index(True)
	        name = known_face_names[first_match_index]
	    
	    else:
	        matches = face_recognition.compare_faces(known_face_encodings, face_encoding, 0.6)
	        if True in matches:
	            first_match_index = matches.index(True)
	            name = known_face_names[first_match_index]
	    
	    if(name == "Unknown"):
	        draw.rectangle(((left, top), (right, bottom)), outline=(255, 0, 0))
	        text_width, text_height = draw.textsize(name)
	        draw.rectangle(((left, bottom - text_height - 10), (right, bottom)), fill=(255, 0, 0), outline=(255, 0, 0))
	        draw.text((left + 6, bottom - text_height - 5), name, fill=(255, 255, 255, 255))

	    else:
	        draw.rectangle(((left, top), (right, bottom)), outline=(0, 0, 255))
	        text_width, text_height = draw.textsize(name)
	        draw.rectangle(((left, bottom - text_height - 10), (right, bottom)), fill=(0, 0, 255), outline=(0, 0, 255))
	        draw.text((left + 6, bottom - text_height - 5), name, fill=(255, 255, 255, 255))	
	
	if(name != "Unknown" or flag == False):
		pil_image.save(path + '\\processed\\' + fname.split('.')[0] + "p.jpg")
	else:
		pil_image.save(path + '\\processed\\' + fname.split('.')[0] + "unknown.jpg")
	#pil_image.show(x.split('.')[0])
	del draw