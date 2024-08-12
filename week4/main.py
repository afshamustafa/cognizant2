from text_processing_tool import count_words, find_unique_words, convert_to_uppercase

def main():
    text = input("Enter a text string: ")

    while True:
        print("\nChoose an option:")
        print("1. Count words")
        print("2. Find unique words")
        print("3. Convert to uppercase")
        print("4. Exit")
        
        choice = input("Enter your choice (1-4): ")
        
        if choice == '1':
            word_count = count_words(text)
            print("Number of words: ",word_count)
        elif choice == '2':
            unique_words = find_unique_words(text)
            print("Unique words: ",unique_words)
        elif choice == '3':
            uppercase_text = convert_to_uppercase(text)
            print("Uppercase text: ",uppercase_text)
        elif choice == '4':
            print("Exiting")
            break
        else:
            print("Invalid choice.")

if __name__ == "__main__":
    main()
